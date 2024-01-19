<template>
    <Navbar :buttonText="navbarButtonText" :buttonDestination="navbarButtonDestination" />
    <div>
        <FindDoctor></FindDoctor>
    </div>
</template>

<script>
import FindDoctor from '../components/FindDoctor.vue';
import Navbar from '../components/NavBar.vue'

export default {
    components: {
        FindDoctor,
        Navbar,
    },
    computed: {
      navbarButtonText() {
        return "Log Out";
      },
      navbarButtonDestination() {
        return "logout";
      }
    },
}
</script>