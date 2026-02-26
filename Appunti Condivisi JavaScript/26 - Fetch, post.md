```html
<body>
    <h1>es03 get</h1>
    <p>Un esempio di POST dal solito sito di test</p>
    <p id="post"></p>
</body>
```
```js
fetch("http://localhost:3000/posts", {
	method: "post",
	headers: {
		'Content-Type': 'application/json'
	},
	body: JSON.stringify({
		id: 5,
		title: "post",
		views: 0
	})
}).then(
	() => fetch("http://localhost:3000/posts/5")
).then(
	response => response.json()
).then((data) => {
	let post = document.getElementById("post");
	post.innerHTML = `post n.${data.id}: title: ${data.title}, views: ${data.views}`;
});
```
```json
{
	"posts": [
		{
			"id": "1",
			"title": "first",
			"views": 100
		},
		{
			"id": "2",
			"title": "second",
			"views": 200
	    },
	    {
			"id": "3",
		    "title": "third",
		    "views": 300
	    }
	]
}
```
