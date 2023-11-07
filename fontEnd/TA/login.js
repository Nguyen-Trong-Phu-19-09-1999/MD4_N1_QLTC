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
    if(data.name == admin) {
        document.getElementById('admin').innerHTML =`
        <div onclick="managerUser()">Manage user</div>`
    }
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