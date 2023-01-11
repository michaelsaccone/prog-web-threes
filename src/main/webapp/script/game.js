function clickButton(direction) {
    fetch('api/matrix/move', {
        method: 'POST',
        headers: {
            'content-type': 'application/x-www-form-urlencoded'
        },
        body: "action=" + direction
    }).then((response) => response.text())
        .then((text) => {
            text = text.replaceAll("\n", '<br>')
            document.getElementById('matrix').innerHTML = text;
        })
}