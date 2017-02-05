import DS from "ember-data";
import {Type} from "../../models/type";

export default DS.Model.extend({
  title: DS.attr('string'),
  description: DS.attr('string'),
  price: DS.attr('number'),
  imagesIds: DS.attr({
    defaultValue () {
      return [];
    }
  }),
  owner: DS.belongsTo(Type.User, {async: false})
});
