<jsp:include page="header.html" />

<div class="container">
  <div class="row justify-content-center align-items-center align-content-center">
    <div class="col-12 col-md-6 text-center">
      <form action="register" method="post">
        <div class="form-group">
          <label for="exampleInputEmail1">Username</label>
          <input type="text" class="form-control" name="username" id="exampleInputEmail1" aria-describedby="emailHelp">
          <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
          <label for="inputName">Nome</label>
          <input type="text" class="form-control" name="name" id="inputName" aria-describedby="nameHelp">
        </div>
        <div class="form-group">
          <label for="surnameInput">Cognome</label>
          <input type="text" class="form-control" name="surname" id="surnameInput">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
    </div>
  </div>

</div>

<jsp:include page="footer.html" />
