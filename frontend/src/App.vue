<script setup>
import Register from './vues/Register.vue';
import Token from './vues/Token.vue';
</script>

<template>
  <div className="body">
  <div className="header">
  <h1>Eggpl🍆nt</h1>
  <p>
    Employee Management Tool
  </p>
</div>

<div className="mainContent">
<div className="leftBox">
  <Register />
</div>

<div className="rightBox">
  <Token />
</div>


</div>
</div>
</template>

<style scoped>
.body {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.header {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: var(--purple);
  background-color: var(--green);
}
.mainContent {
  background-image: url("@/assets/image.png");
  height:100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
.leftBox {
padding: 1rem;
width: 100%;
}
.rightBox {
padding: 1rem;
width: 100%;
}
</style>
<style>
:root {
  --green: #349948;
  --greenlite: #c2e0c8;
  --purple: #43217e;
  --purplelite: #ebd6e7;
  --blue: #345299;
  --orange: #997a34;
}
html {
  height: 100%;
  background-image: url("/assets/image.png");
}
body{
  margin:0;
  height:100%;
  background-image: url("/assets/image.png");
}
#app{
  height:100%;
}
</style>
