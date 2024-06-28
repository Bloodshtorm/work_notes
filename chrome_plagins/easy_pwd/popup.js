document.addEventListener("DOMContentLoaded", function () {
  document.getElementById("cleanButton").addEventListener("click", function () {
    const inputPassword = document.getElementById("inputPassword").value;
    const cleanedPassword = inputPassword.replace(/[^a-zA-Z0-9]/g, "");
    document.getElementById("outputPassword").value = cleanedPassword;
  });
});
