function showFormAddTran(){
    document.getElementById('body').innerHTML = `
    <table>
    <h2>Add transaction type</h2>
        <tr>
        <td><input type="text" id="name"></td>
        </tr>        
        <tr>
        <td><button onclick="AddTransactionType()">Add</button></</td>
</tr>
    </table>`
}
function AddTransactionType() {
    let data =
        {
            name: document.getElementById("name").value
        }
    axios.post("http://localhost:8080/api/transactionType", data).then(() => {

        alert("Add success")
        showAllTransaction()

    });

}

