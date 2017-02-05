import DS from "ember-data";
import {Type} from "../../models/type";

export default DS.Model.extend({
  name: DS.attr('string'),
  email: DS.attr('string'),
  products: DS.hasMany(Type.Product, {async: false})
});
