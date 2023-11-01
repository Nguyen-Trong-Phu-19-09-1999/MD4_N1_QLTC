function login(){
    let data =
        {
            name: document.getElementById("name").value,
            password: document.getElementById("psw").value
        }
    axios.post("http://localhost:8080/users/login", data).then(() => {
        console.log(data)
        alert("Login success")

    });

}
function showFormLogin(){
    document.getElementById('body').innerHTML = `

    <table>
        <tr>
        <td><input type="text" id="name"></td>
        </tr>
        <tr>
        <td><input type="text" id="psw"></td>
        </tr>
         <tr>
         <td><button onclick="login()">Login</button></tr>
         </td>
</tr>
    </table>`
}