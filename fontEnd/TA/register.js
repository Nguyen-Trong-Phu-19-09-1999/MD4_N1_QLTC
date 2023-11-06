
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
        window.location.href = '../templates/all-admin-login.html'
        alert("Register success")

    }).catch((error) => {
        if (error.response) {
            if (error.response.status === 400) {
                alert("Đăng ký không thành công. Vui lòng kiểm tra lại thông tin.");
            } else {
                console.error("Server error:", error.response.data);
                alert("Đã xảy ra lỗi khi đăng ký");
            }
        } else if (error.request) {
            console.error("No response received from the server");
            alert("Không có phản hồi từ máy chủ");
        } else {
            console.error("Request setup error:", error.message);
            alert("Đã xảy ra lỗi khi thiết lập yêu cầu");
        }
    });

}