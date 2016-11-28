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

<hr/>
</body>
</html>