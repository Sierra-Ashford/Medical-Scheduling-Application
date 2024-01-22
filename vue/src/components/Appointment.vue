<template>
    <div class="appointment">
        <p class="appointment-message">
            {{ appointment-message }} </p>
           
    </div>
</template>


<script>
export default{
    props: {
        appointment:{
            type:Object,
            default:null
        }
    },
   
}








</script>

<style scoped>
.appointment {
    border-radius: 10px;
    padding: 4px 12px;
    width: 500px;
    background-color: #587DFF;
    color: white;
    font-weight: 600;
    margin-bottom: 20px;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: center;
    justify-content: center;
}

.appointment-message {
    display: flex;
    flex-direction: column;
    flex-basis: 60%;
    flex: 1
}
</style>