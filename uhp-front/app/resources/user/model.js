import DS from "ember-data";
import {Types} from "../../models/types";

export default DS.Model.extend({
  name: DS.attr('string'),
  email: DS.attr('string'),
  products: DS.hasMany(Types.Product)
});
