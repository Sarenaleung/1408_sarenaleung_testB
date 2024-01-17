const express = require('express');
const app = express();
app.use(express.static('public'));
app.use(express.urlencoded({extended: true}))

app.post('/calculate-shipping-fee', (req, res) => {

 console.log(req.body);
 const temperature = req.body.temperature;
 const items = Object.values(req.body)
    .filter((key) => key && typeof key === 'object')
    .map((item) => ({
        length: item.length,
        width: item.width,
        height: item.height,
        weight: item.weight
    }));

   let shippingFee = 0;

  for (let i = 0; i < items.length; i++){
    let item = items[i]
    volumetricWeight = (item.length*item.width*item.height)/5000;
    let weight = item.weight > volumetricWeight? item.weight : volumetricWeight;
    switch(temperature){
        case "Ambient":
            if(weight <=5)
                shippingFee += 10;
            else
                shippingFee += 15;
            break;
        case "Chill":
            if(weight <=5)
                shippingFee += 20;
            else
                shippingFee += 30;
            break;
    }

  }
  console.log(shippingFee);

  res.json({shippingFee});
     
});



// Start the server
app.listen(8000, () => {
  console.log('Server is running on port 8000');
});