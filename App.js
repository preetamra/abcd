import { StatusBar } from 'expo-status-bar';
import React , {useEffect} from 'react';
import { StyleSheet, Text, View,Pressable,NativeModules,NativeEventEmitter} from 'react-native';
export default function App() {
  const {CustomModule} = NativeModules

  useEffect(
  () => {
  const eventEmitter = new NativeEventEmitter(NativeModules.ToastExample);
   this.eventListener = eventEmitter.addListener('EventReminder', (event) => {
     const isUrlContainsArrItem = (arr, url) => {
    for(let item of arr){
        if(url.includes(item)) return true;
    }
    
    return false;
}     

 console.log(isUrlContainsArrItem(badWordsArray,event.event),event.event) // "someValue"
   });
},[]  
);

  return (
    <View style={styles.container}>
      <Pressable style={styles.button} onPress={() => { 
        console.log("Button Pressed",CustomModule.getEvent());
      }}>
    <Text style={styles.buttonLabel}>Press Me</Text>
  </Pressable>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  buttonContainer: {
    width: 320,
    height: 68,
    marginHorizontal: 20,
    alignItems: 'center',
    justifyContent: 'center',
    padding: 3,
  },
  button: {
    borderRadius: 10,
    width: '100%',
    height: '100%',
    backgroundColor:"black",
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
  },
  buttonIcon: {
    paddingRight: 8,
  },
  buttonLabel: {
    color: '#fff',
    fontSize: 16,
  },
});
