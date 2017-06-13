function getFormAddRessource() {
    return `<div class="row">
                <form id="formAddRessource" class="col s12" method="POST" action="/TP3/ressources/add">
                        <div class="input-field col s12">
                            <input id="ressourceName" name="ressourceName" type="text" class="validate">
                            <label for="ressourceName" class="">Nom de la ressource</label>
                        </div>
                </form>
            </div>`;
}