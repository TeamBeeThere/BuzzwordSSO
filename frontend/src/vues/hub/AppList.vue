<script setup>


let apps = [
  { name: 'BeeThere', image: 'htmlicon.svg', color: 'var(--greenlite)', path: '/bee-there' },
  { name: 'SailBoat', image: 'htmlicon.svg', color: 'var(--purplelite)', path: '/sail-boat' },
  { name: 'EggPlant', image: 'htmlicon.svg', color: 'var(--purple)', path: '/egg-plant' },
  { name: '', image: '', color: 'var(--buzzlite)', path: '' },
  { name: '', image: '', color: 'var(--buzzlite)', path: '' },
  { name: '', image: '', color: 'var(--buzzlite)', path: '' },
];

const navigateToApp = (path) => {
  router.push(path);
};

</script>


<template>
  <div class="mainCard">
    <div 
      v-for="(app, index) in apps" 
      :key="index"
      class="appCard" 
      @click="navigateToApp(app.path)" :style="{ backgroundColor: app.color }"
    >
     <img v-if="app.image && app.name" :src="`/src/assets/${app.image}`" :alt="app.name" width="50" height="50" />
      <p>{{ app.name }}</p>
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
.mainCard {
  background-color: var(--buzzGray);
  height:100%;
  width: 80%;
  display: flex;
  gap: .5rem;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-evenly;
}
.appCard {
    height: 10rem;
    width: 10rem;
  padding: 1rem;
  margin: 1rem;
  border-radius: 8px;
  background-color: white;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  transition: transform 0.2s;
}

.appCard:hover {
  transform: scale(1.05);
}

.loginForm {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>