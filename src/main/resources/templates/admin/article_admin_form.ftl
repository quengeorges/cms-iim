<#include "../base.ftl">
<#macro page_head>
    <title>Create article Admin</title>
</#macro>

<#macro page_body>
    <form action="/admin/article/add" method="post">
        <input type="text" name="title">
        <textarea name="content" cols="30" rows="10"></textarea>
        <button type="submit">Ajouter</button>
    </form>

</#macro>

<@display_page/>