<#include "../base.ftl">
<#macro page_head>
    <title>List Article Admin</title>
</#macro>

<#macro page_body>
    <h1 class="text-center my-lg-5">Liste des articles</h1>


    <div style="padding: 10px" class="row">
        <#list list as article>
            <div class="col-md-4">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">${article.title}</h5>
                        <p>${article.text}</p>
                        <div>
                            <a href="/article/${article.id}" class="btn btn-primary">Lire</a>
                            <a href="/admin/article/del/${article.id}" class="btn btn-warning">Supprimer</a>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    </div>

    <div class="ml-2 mt-lg-5">
        <a class="btn btn-primary" href="/admin/article/form">Ajouter un article</a>
    </div>
</#macro>

<@display_page/>