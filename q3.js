function calculateMaxQuantity(cartonBoxSize, productSize) {
    cartonBoxVolumn = cartonBoxSize.length * cartonBoxSize.width * cartonBoxSize.height;
    productVolumn = productSize.length * productSize.width * productSize.height;
    return Math.floor(cartonBoxVolumn / productVolumn);
}

const cartonBoxSize = { length: 320, width: 260, height: 200 };
const productSize = { length: 210, width: 35, height: 35 };

const maxQuantity = calculateMaxQuantity(cartonBoxSize, productSize);
console.log(maxQuantity) //result: 64