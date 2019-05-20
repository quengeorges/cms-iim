<#include "base.ftl">
<#macro page_head>
    <title>Article</title>
</#macro>

<#macro page_body>
    <h1>${title}</h1>
    <p>${text}</p>

    <form action="/comment/add" method="post">
        <textarea name="text" cols="30" rows="10"></textarea>
        <input type="hidden" value="${id}" name="article_id">
        <button type="submit">Ajouter</button>
    </form>
</#macro>

<@display_page/>