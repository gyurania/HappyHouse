import { houseInstance } from "./index.js";

const house = houseInstance();

async function houseList(params, success, fail) {
  await house.get(``, { params: params }).then(success).catch(fail);
}

export { houseList };
