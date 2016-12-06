<html>
<body>
<hr/>
<h1>This is the test app</h1>
<h2>The task is:</h2>

<p>Build a voting system for deciding where to have lunch.</p>
<ul>
    <li>2 types of users: admin and regular users</li>
    <li>Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)</li>
    <li>Menu changes each day (admins do the updates)</li>
    <li>Users can vote on which restaurant they want to have lunch at</li>
    <li>Only one vote counted per user. If user votes again the same day:
        <ul>
            <li>If it is before 11:00 we asume that he changed his mind.</li>
            <li>If it is after 11:00 then it is too late, vote can't be changed</li>
        </ul>
    </li>
    <li>Each restaurant provides new menu each day.</li>
</ul>
<h3>Note:</h3>
<p>Before run, change date in file populateDB.sql to today. Lunch time is 15.00</p>
<p> mapping "/" - root mapping (this page)</p>
<p> mapping "/create" (method Post) - register new user (json body required) </p>
<p>mapping "/dishes"</p>
<ul>
    <li> "/" (method Get) - get dishes list with restaurants. For any authenticated user</li>
    <li> "/" (method Post) - create new dish (json body required). Only for users with Role "ROLE_ADMIN"</li>
    <li> "/{id}" (method Get) - get dish with id. Only for users with Role "ROLE_ADMIN"</li>
    <li> "/{id}" (method Put) - update dish with id (json body required). Only for users with Role "ROLE_ADMIN"</li>
    <li> "/{id}" (method Delete) - delete dish with id. Only for users with Role "ROLE_ADMIN"</li>
</ul>
<p> mapping "/voices" - (method Post) - register new user (json body required). Only for unauthenticated users</p>
<ul>
    <li> "/" (method Get) - get restaurant list with voices count. For any authenticated user</li>
    <li> "/" (method Post) - create new voice (json body required). Only for users with Role "ROLE_USER"</li>
    <li> "/{id}" (method Get) - get voice with id. Only for users with Role "ROLE_ADMIN"</li>
    <li> "/{id}" (method Put) - update voice with id (json body required). Only for users with Role "ROLE_ADMIN"</li>
    <li> "/{id}" (method Delete) - delete voice with id. Only for users with Role "ROLE_ADMIN"</li>
</ul>
<p> mapping "/users" (All points only for users with Role "ROLE_ADMIN")</p>
<ul>
    <li> "/" (method Get) - get users list.</li>
    <li> "/" (method Post) - create new user (json body required).</li>
    <li> "/{id}" (method Get) - get user with id.</li>
    <li> "/{id}" (method Put) - update user with id (json body required).</li>
    <li> "/{id}" (method Delete) - delete user with id.</li>
</ul>
<p> mapping "/restaurants" (All points only for users with Role "ROLE_ADMIN")</p>
<ul>
    <li> "/" (method Get) - get restaurants list.</li>
    <li> "/" (method Post) - create new restaurant (json body required).</li>
    <li> "/{id}" (method Get) - get restaurant with id.</li>
    <li> "/{id}" (method Put) - update restaurant with id (json body required).</li>
    <li> "/{id}" (method Delete) - delete restaurant with id.</li>
</ul>
<p></p>

</body>
</html>