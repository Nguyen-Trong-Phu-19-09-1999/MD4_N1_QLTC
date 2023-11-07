function loadUser(){
    if(name ==null){
        window.location.href = "all-admin-login.html"
    }else {
        document.getElementById('user-main').innerHTML= `<div>
          xin chao ${name}
          <div onclick="logout()">Logout</div>
          </div>`

    }
}
function logout(){
    localStorage.removeItem('name');
    localStorage.removeItem('userId');
    localStorage.removeItem('username');
    loadUser();
}