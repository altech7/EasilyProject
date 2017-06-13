package fr.esimed.easilyproject.BS;

import fr.esimed.easilyproject.DAO.IDAO;
import fr.esimed.easilyproject.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Repository
@Scope("singleton")
@Transactional
public class ClientBS implements IBusinessService {

    private static final Logger LOG = LoggerFactory.getLogger(ClientBS.class);

    @Inject
    IDAO clientDAO;

    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    public Client insert(final Client client) {
        return (Client) clientDAO.merge(client);
    }

}
