$(function() {
  $("#loginBtn", ".login").button();
  $("#loginBtn", ".login").click(
    function() {
      location.href="main.html";
    }
  );
});