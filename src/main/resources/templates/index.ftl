<#include "base.ftl">
<#macro page_head>
    <title>List Article</title>
</#macro>

<#macro page_body>
    <h1 class="text-center my-lg-5">Liste des articles</h1>

    <div style="padding: 10px" class="row">
        <#list list as article>
            <div class="col-md-4">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">${article.title}</h5>
                        <p class="card-text">${(article.text?length < 26 )?then(article.text, article.text?substring(0,26) + "...")}</p>
                        <a href="/article/${article.id}" class="btn btn-primary">Lire</a>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</#macro>

<@display_page/>