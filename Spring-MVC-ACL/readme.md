Skeleton project using Netbeans 8, jdk 1.8, Maven, spring 4.3.8, spring security 4.2, AOP and ACL.

<h2>How to start:</h2>
<ul>
    <li>Open project with netbeans and run it (http://localhost:8080).</li>
    <li>You have to register with email address and password from <a href="/register">Register</a> link on top of page or from side link.</li>
    <li><a href="/login">Login</a> with your info.</li>
    <li>After login your will be redirected to item listing page.</li>
    <li>Start to create new item, after creation you will be redirected to item listing page.</li>
    <li>From item listing page you can show more details about the item.</li>
    <li>From item listing page you can delete an item.</li>
    <li>Item listing page, list only items according to current authenticated user permission.</li>
</ul>
<h2>Security:</h2>
<ul>
    <li>Implemented user role management using spring security, i build my custom user details class and used it in the authentication context.</li>
    <li>Secured urls using spring security URL interceptors.</li>
    <li>Enabled <code>@Secured</code> annotation and used it on ItemDAO to secure the services for authenticated users, ex <code>@Secured(SystemConfiguration.AUTHENTICATED_USER_ROLE)</code>.</li>
    <li>Used Spring ACL to manage the item permissions.</li>
    <li>Created interface <code>AclManager</code> with <code>AclManagerImpl</code> implementation to manage ACL Permissions.</li>
    <li>Created domain object as well as an Acl entry for item domain on item addition.</li>
    <li>Used the User.id as sid on acl_sid table.</li>
    <li>Used <code>@PostFilter</code> annotation on method findAllOrderById to filter the returned collection according to user permission.<code>@PostFilter("hasPermission(filterObject, 'administration')")</code></li>
    <li>Used <code>@PostAuthorize</code> annotation on method findById to filter the check the returned object according to user permission.<code>@PostAuthorize("hasPermission(returnObject, 'administration')")</code></li>
    <li>Used <code>@PreAuthorize</code> on delete method (<code>@PreAuthorize("hasPermission(#item, 'administration')")</code>)</li>
    <li>Added a security interceptor to all item pages for authenticated users only <code> &lt;intercept-url pattern="/**item**" access="isAuthenticated()" /&gt;</code></li>
    <li>All user rest services miss a security</li>
</ul>
<h2>JUnit:</h2>
<ul>
    <li>Added 5 test cases on UserServiceTest to test UserService.</li>
    <li>Added 8 test cases on ItemServiceTest to test ACL, anonymous user and authenticated user, anonymous execute find and findAll, user try to find an item of another user, findAll load items of the authenticated user only.</li>
    <li>Added 5 test cases on UserControllerTest to consume the UserController rest services(You need to run the test first to be able to consume the resources).</li>
</ul>
<h2>Database:</h2>
<ul>
    <li>Added file data.ql on project main resources with ACL tables.</li>
    <li>Updated url parameter on datasource to run the data.sql on init <code>jdbc:h2:mem:microservice;INIT=RUNSCRIPT FROM 'classpath:data.sql'</code>.</li>
</ul>