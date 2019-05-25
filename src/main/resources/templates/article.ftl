<#include "base.ftl">
<#macro page_head>
    <title>Article</title>
</#macro>

<#macro page_body>
    <h1 class="my-lg-5 text-center">${article.title}</h1>
    <p class="text-center">${article.text}</p>

    <div class="px-lg-5">
        <div class="card p-lg-5">
            <form action="/comment/add" method="post">
                <div class="form-group">
                    <label for="textAreaContent">Commentaire :</label>
                    <textarea class="form-control" id="textAreaContent" rows="10" cols="30" name="text" ></textarea>
                </div>
                <input type="hidden" value="${article.id}" name="article_id">
                <button type="submit" class="btn btn-primary">Commenter</button>
            </form>

            <ul>
                <#list article.comments as comment>
                    <div class="card my-lg-2">
                        <div class="card-body">
                            <p class="card-text">${comment.content}</p>
                        </div>
                    </div>
                </#list>
            </ul>
        </div>
    </div>

</#macro>

<@display_page/>