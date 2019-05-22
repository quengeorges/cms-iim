<#include "../base.ftl">
<#macro page_head>
    <title>List Article Admin</title>
</#macro>

<#macro page_body>
    <ul>
        <#list list as article>
            <li>
                <a href="/article/${article.id}">${article.title}</a>
                <a href="/admin/article/del/${article.id}">Delete</a>
            </li>
        </#list>
    </ul>

    <div>
        <a href="/admin/article/form">Ajouter un article</a>
    </div>
</#macro>

<@display_page/>