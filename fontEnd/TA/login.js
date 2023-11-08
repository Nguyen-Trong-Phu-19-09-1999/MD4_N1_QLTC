function login() {
    let data = {
        username: document.getElementById("name").value,
        password: document.getElementById("psw").value,
    };

    axios.post("http://localhost:8080/login", data)
        .then((response) => {
            const { id, username, token, accessToken,roles} = response.data;
            console.log(response.data)
            localStorage.setItem('userId', id);
            localStorage.setItem('name', username);
            localStorage.setItem("token", accessToken);
            localStorage.setItem("role", roles)
            window.location.href = "../templates/alldata.html";
            if(username === admin) {
                document.getElementById('admin').innerHTML =`<div onclick="managerUser()">Manage user</div>`
            }
            loadUser();
        })
        .catch((error) => {
            if (error && error.response && error.response.status === 401) {
                alert("Sai thông tin đăng nhập");
            } else {
                console.error("Login error:", error);
            }
        });
}
function managerUser(){
    axios.get('http://localhost:8080/users').then((res) =>{
        let data = res.data;
        for (let i= 0; i< data.length; i++ ){
            str += `<div>${data[i].name}<button onclick="deleteUser(${data[i].id})">Delete</button></div>`
        }
        document.getElementById('body').innerHTML = str;
    });

}
function deleteUser(id) {
    axios.delete('http://localhost:8080/users/' + id).then(res => {

        managerUser();
    });
}
