<#include "../base.ftl">
<#macro page_head>
    <title>Create article Admin</title>
</#macro>

<#macro page_body>
    <h1 class="text-center my_lg-5">Ajouter un article</h1>

    <form action="/admin/article/add" method="post" class="col-lg-6">
        <div class="form-group">
            <label for="inputTitre">Titre de l'article</label>
            <input type="text" class="form-control" id="inputTitre" placeholder="Entrer le titre">
        </div>
        <div class="form-group">
            <label for="textAreaContent">Contenu de l'article</label>
            <textarea class="form-control" id="textAreaContent" rows="10" cols="30" name="content" ></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Soumettre</button>
    </form>

</#macro>

<@display_page/>