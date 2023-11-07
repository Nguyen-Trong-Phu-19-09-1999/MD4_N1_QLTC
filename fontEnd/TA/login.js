
let str ='';
function login() {
    let data = {
        username: document.getElementById("name").value,
        password: document.getElementById("psw").value,
    };

    axios.post("http://localhost:8080/login", data)
        .then((response) => {
            const { id, username } = response.data;
            localStorage.setItem('userId', id);
            localStorage.setItem('name', username);
            window.location.href = "../templates/all-admin-datalist.html";
            // if(data.username === admin) {
            //     document.getElementById('admin').innerHTML =`<div onclick="managerUser()">Manage user</div>`
            // }
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
 async function managerUser(){
    console.log('ok')
 await   axios.get('http://localhost:8080/users').then((res) =>{
        let data = res.data;
        for (let i= 0; i< data.length; i++ ){
            str += `<div>${data[i].name}
            </div>`
            if(localStorage.getItem('name') == 'admin'){
                str += `<button onclick="deleteUser(${data[i].id})">Delete</button>`
            }
        }
        document.getElementById('body').innerHTML = str;
    });
}
function deleteUser(id) {
    axios.delete('http://localhost:8080/users/' + id).then(res => {

        managerUser();
    });
}
function showFormLogin(){
    document.getElementById('body').innerHTML = `
    <table>
    <h2>Login</h2>
        <tr>
        <td><input type="text" id="name" placeholder=" Name: "></td>
        </tr>
        <tr>
        <td><input type="password" id="psw" placeholder="password : "></td>                
        </tr>
        <tr>
        <td><button onclick="login()">Login</button></</td>
</tr>
    </table>`

}
