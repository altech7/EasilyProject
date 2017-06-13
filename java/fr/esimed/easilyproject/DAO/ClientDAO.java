package fr.esimed.easilyproject.DAO;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;
import fr.esimed.easilyproject.entity.Client;
import fr.esimed.easilyproject.entity.QClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Scope("singleton")
@Transactional
public class ClientDAO extends AbstractDAO<Client> {

    private static final QClient CLIENT = QClient.client;

    @Override
    protected Client create() {
        return new Client();
    }

    @Override
    protected EntityPathBase<Client> getPath() {
        return CLIENT;
    }

    public List<Client> findAll() {

        final JPAQuery query = getQuery();

        query.from(CLIENT);

        return query.list(CLIENT);
    }
}
