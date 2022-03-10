<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      crossorigin="anonymous"
    />
    <style>
      .restrictwidth {
        max-width: 450px;
        border-width: 5px !important;
      }
    </style>
</head>
 <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="#">Navbar</a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNavAltMarkup"
        aria-controls="navbarNavAltMarkup"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
          <a class="nav-item nav-link active" href="#"
            >Home <span class="sr-only">(current)</span></a
          >
          <a class="nav-item nav-link" href="#">Register a Patient</a>
        </div>
      </div>
    </nav>
    <br /><br />
    <div id="restrictwidth" class="container border pt-4 pb-4 rounded-top">
      <h5 class="text-center">Enter Patient Id</h5>
      <form
        method="post"
        action="http://localhost:8080/submit/patientID"
        class="needs-validation"
        novalidate
      >
        <div class="form-row mt-4 d-flex justify-content-center form-group">
          <br />
          <label for="patientID" class="form-label col-md-1">Patient ID</label>
          <div class=":col-md-4">
            <input
              type="text"
              class="form-control"
              name="id"
              id="patientID"
              required
              minlength="7"
              maxlength="7"
              pattern="^[P]{1}[0-9]{6}"
            />

            <div class="invalid-feedback">
              Patient Id starts with P followed by 6 numerical characters
            </div>
          </div>
        </div>
        <div class="text-center">
          <button
            class="btn btn-primary form-row justify-content-center"
            type="submit"
          >
            Submit form
          </button>
        </div>
      </form>
    </div>
    <script>
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function () {
        "use strict";
        window.addEventListener(
          "load",
          function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName("needs-validation");
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(
              forms,
              function (form) {
                form.addEventListener(
                  "submit",
                  function (event) {
                    if (form.checkValidity() === false) {
                      event.preventDefault();
                      event.stopPropagation();
                    }
                    form.classList.add("was-validated");
                  },
                  false
                );
              }
            );
          },
          false
        );
      })();
    </script>
  </body>
</html>