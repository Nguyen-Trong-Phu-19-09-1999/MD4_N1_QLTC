function login() {
    let data = {
        username: document.getElementById("name").value,
        password: document.getElementById("psw").value,
    };

    axios.post("http://localhost:8080/login", data)
        .then((response) => {
            localStorage.setItem('name', data.username);
            window.location.href = "../templates/all-admin-datalist.html";
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

function showFormLogin(){
    document.getElementById('body').innerHTML = `

    <table>
    <h2>Login</h2>
        <tr>
        <td><input type="text" id="name"></td>
        </tr>
        <tr>
        <td><input type="password" id="psw"></td>
        </tr>
         <tr>
         <td><button onclick="login()">Login</button></tr>
         </td>
</tr>
    </table>`
}