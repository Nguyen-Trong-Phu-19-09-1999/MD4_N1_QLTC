function login(){
    let data =
        {
            name: document.getElementById("name").value,
            password: document.getElementById("psw").value
        }
    axios.post("http://localhost:8080/users/login", data).then(() => {
        localStorage.setItem('name',document.getElementById("name").value)
        alert("Login success")
        window.location.href = "../templates/all-admin-datalist.html";
        loadUser();
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