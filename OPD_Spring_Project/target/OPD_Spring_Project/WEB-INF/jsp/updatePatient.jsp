<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" import ="beans.web.model.Patient" %>
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
</head>
<body>
<h1>Patient Update</h1>
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
    <div class="container border pt-4 pb-4 rounded-top">
      <form
        method="post"
        action="http://localhost:8080/submit/updatepatient"
        class="needs-validation"
        novalidate
      >
        <div class="form-row mt-4 d-flex justify-content-center form-group">
          <label for="patientID" class="form-label col-md-1">Patient ID</label>
          <div class=":col-md-4">
            <input value="${patient.getPatientId()}"
              type="text"
              class="form-control"
              name="patientId"
              id="patientID"
              required
              minlength="7"
              maxlength="7"
              pattern="^[P]{1}[0-9]{6}"
              readonly="true"
            />
          </div>
        </div>
        <div class="form-row d-flex mt-4 justify-content-center form-group">
          <label for="firstNameId" class="col-md-1 form-label"
            >First Name</label
          >
          <div class="col-md-4">
            <input value="${patient.getFirstName()}"
              type="text"
              name="firstName"
              id="firstNameId"
              class="form-control"
              placeholder="First name"
              aria-label="First name"
              minlength="5"
              required
              pattern="^[a-zA-Z]+$"
            />
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">
              First Name should be atleast 5 letters long and should contain
              only characters.
            </div>
          </div>
          <label for="lastNameId" class="col-md-1 col-form-label"
            >Last Name</label
          >
          <div class="col-md-4">
            <input value="${patient.getLastName()}" 
              type="text"
              name="lastName"
              id="lastNameId"
              class="form-control"
              placeholder="Last name"
              aria-label="Last name"
              minlength="5"
              required
              pattern="^[a-zA-Z]+$"
              value="omesh"
            />
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">
              Last Name should be atleast 5 letters long and should contain only
              characters.
            </div>
          </div>
        </div>

        <div class="form-row d-flex mt-4 justify-content-center form-group">
          <label for="inputEmail3" class="col-md-1 col-form-label">Email</label>
          <div class="col-md-4">
            <input value="${patient.getEmail()}" 
              type="email"
              name="email"
              class="form-control"
              id="inputEmail3"
              placeholder="Email"
              required
              pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"
            />
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Please enter valid email</div>
          </div>
          <label for="PhoneNumber" class="col-md-1 col-form-label">Phone</label>
          <div class="col-md-4">
            <input value="${patient.getPhoneNumber()}"
              type="text"
              name="phoneNumber"
              class="form-control"
              id="phoneNumber"
              placeholder="1234567890 or 123-456-7890"
              required
              pattern="^[0-9]{10}$|^[0-9]{3}[-]{1}[0-9]{3}[-]{1}[0-9]{4}$"
            />
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Phone Number invalid</div>
          </div>
        </div>
        <div class="form-row mt-4 d-flex justify-content-center form-group">
          <label for="inputAddress" class="col-md-1 col-form-label"
            >Address</label
          >
          <div class="col-md-9">
            <input value="${patient.getAddress()}"
              type="text"
              name="address"
              class="form-control"
              id="inputAddress"
              placeholder="1234 Main St"
              required
            />
          </div>
        </div>
        <div class="form-row mt-4 d-flex justify-content-center form-group">
          <label for="cityid" class="col-md-1 col-form-label">City</label>
          <div class="col-md-4">
            <input value="${patient.getCity()}"
              type="text"
              name="city"
              class="form-control"
              id="cityid"
              placeholder="Mumbai"
              minlength="3"
              required
              pattern="^[a-zA-Z]+$"
            />
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">
              City name should be atleast 3 letters long and should contain only
              characters.
            </div>
          </div>
          <label for="stateid" class="col-md-1 col-form-label">State</label>
          <div class="col-md-4">
            <input value="${patient.getState()}"
              type="text"
              name="state"
              class="form-control"
              id="stateid"
              placeholder="Maharashtra"
              minlength="3"
              required
              pattern="^[a-zA-Z]+$"
            />
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">
              State should be atleast 3 letters long and should contain only
              characters.
            </div>
          </div>
        </div>
        <div class="form-row mt-4 d-flex justify-content-center form-group">
          <label for="genderId" class="col-md-1 col-form-label">Gender</label>
          <select value="${patient.getGender().toString()}" class="form-control col-md-2" id="genderId" name="gender">
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
            <option value="OTHER">Other</option>
          </select>
          <label for="dob" class="col-md-1 col-form-label">Birth-Date</label>
          <input value="{patient.getDateOfBirth().toString()}"
            type="date"
            class="col-md-2 form-control col-form-label"
            id="dob"
            name="dateOfBirth"
            required
          />
          <label class="col-md-2 col-form-label">Social Security Number</label>
          <div class="col-md-2">
            <input value="${patient.getSSN()}"
              name="SSN"
              pattern="^(\d{3}-?\d{2}-?\d{4})$"
              class="form-control"
              title="###-##-####"
              required
             readonly="true"
            />
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Invalid SSN number</div>
          </div>
        </div>
        <div class="mt-5 text-center">
          <button
            class="btn btn-primary form-row justify-content-center"
            type="submit"
          >
            Submit
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