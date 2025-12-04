<script setup>
</script>

<template>
<div className="vueCard">
<form className="loginForm">
    <label>Username</label><input type="text" placeholder="Username" /><br/><br/>
    <label>Password</label><input type="password" placeholder="Password" /><br/><br/>
    <button type="submit">Login</button>
</form>


</div>
</template>

<style scoped>
html {
  height: 100%;
}
body{
  margin:0;
  height:100%;
}
.body {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.header {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: left;
  color: black;
  background-color: var(--buzzlite);
}

.header h1 {
     margin-left: 2rem;
}
.mainContent {
  background-color: var(--buzzGray);
  height:100%;
  display: flex;
  gap: 1rem;
  flex-direction: row;
  justify-content: space-evenly;
}
.vueCard {
padding: 1rem;
height: 10rem;
width: 15rem;
border-radius: 10%;
background-color: var(--buzzlite);
margin-top: 3rem;
display: flex;
justify-content: center;
align-items: center;
}
.loginForm {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>