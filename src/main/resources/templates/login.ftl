<#include "base.ftl">
<#macro page_head>
    <title>Login</title>
</#macro>

<#macro page_body>
    <h1>Login</h1>

    <form action="" method="post">
        <input type="email" name="mail" />
        <input type="password" name="password" />
        <button type="submit">Login</button>
    </form>
</#macro>

<@display_page/>