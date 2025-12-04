<script setup>
import RegisterVue from './vues/Register.vue';
import TokenVue from './vues/Token.vue';
</script>

<template>
  <div className="body">
  <div className="header">
  <img src='./assets/eggplant.png' alt="Eggplant" />
  <p>
    Employee Management Tool
  </p>
</div>

<div className="mainContent">
  <!-- Left Box -->
<div className="vueCard">
  <RegisterVue />
</div>

<!-- Right Box -->
<div className="vueCard">
  <TokenVue />
</div>


</div>
</div>
</template>

<style scoped>
.body {
  height: 100%;
  display: flex;
  gap: 3rem;
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
  gap: 1rem;
  flex-direction: row;
  justify-content: space-evenly;
}
.vueCard {
padding: 1rem;
height: 25rem;
width: 20rem;
border-radius: 10%;
background-color: var(--green);
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
}
body{
  margin:0;
  height:100%;
  background-image: url("@/assets/royalBackground.png");
}
#app{
  height:100%;
}
</style>
