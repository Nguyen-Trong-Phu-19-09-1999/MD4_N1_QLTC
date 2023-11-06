
function showFormRegister(){

    document.getElementById('body').innerHTML = `
    <table>
    <h2>Register</h2>
        <tr>
        <td><input type="text" id="name" placeholder=" Name: "></td>
        </tr>
        <tr>
        <td><input type="password" id="password" placeholder="password : "></td>                
        </tr>
        <tr>
        <td><button onclick="register()">Register</button></</td>
</tr>
    </table>`
}
function register(){
    let data =
        {
            username: document.getElementById("name").value,
            password: document.getElementById("password").value,
            confirmPassword: document.getElementById("confirm").value,
        }

    axios.post("http://localhost:8080/register",data).then(() => {

        alert("Register success")

    });

}