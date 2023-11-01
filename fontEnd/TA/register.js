
function showFormRegister(){

    document.getElementById('body').innerHTML = `
    <table>
        <tr>
        <td><input type="text" id="name" placeholder=" Name: "></td>
        </tr>
        <tr>
        <td><input type="text" id="password" placeholder="password : "></td>                
        </tr>
        <tr>
        <td><button onclick="register()">Register</button></</td>
</tr>
    </table>`
}
function register(){
    let data =
        {
            name: document.getElementById("name").value,
            password: document.getElementById("password").value,
            role: {
                id: 2
            }
        }

    axios.post("http://localhost:8080/users/register", data).then(() => {

        alert("Register success")

    });

}