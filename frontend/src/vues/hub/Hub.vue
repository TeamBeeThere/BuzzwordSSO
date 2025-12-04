<script setup>
import Login from './Login.vue'
import AppList from './AppList.vue'
</script>

<template>
  <div className="body">
  <div className="header">
    <h1>Buzzword Software Solutions</h1>
</div>

<div className="mainContent">

  <!-- <Login />  -->
  <AppList/>

</div>
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
height: 25rem;
width: 20rem;
border-radius: 10%;
background-color: var(--buzzlite);
margin-top: 3rem;
}
</style>
