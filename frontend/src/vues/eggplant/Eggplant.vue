<script setup>
import RegisterVue from './Register.vue';
import TokenVue from './Token.vue';
</script>

<template>
  <div className="body">
  <div className="header">
  <img src='../../assets/eggplant.png' alt="Eggplant" />
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
  background-image: url("@/assets/royalBackground.png");
}

.header {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: var(--purple);
  background-color: var(--green);
}
.mainContent {
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

