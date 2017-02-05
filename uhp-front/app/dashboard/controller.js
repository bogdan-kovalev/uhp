import Ember from "ember";
import {Type} from "../models/type";

export default Ember.Controller.extend({
  showDialog: false,
  newProduct: null,
  usersService: Ember.inject.service(),

  owner: Ember.computed.readOnly('usersService.account'),

  actions: {
    showAddProduct() {
      this.toggleProperty('showDialog');
      const record = this.get('store').createRecord(Type.Product, this.getProperties('owner'));
      this.set('newProduct', record);
    },

    cancel () {
      this.get('store').unloadRecord(this.get('newProduct'));
      this.set('newProduct', null);
      this.toggleProperty('showDialog');
    },

    save () {
      this.toggleProperty('saving');
      this.get('newProduct').save()
        .then(() => {
          this.toggleProperty('showDialog');
        })
        .finally(() => {
          this.toggleProperty('saving');
        });
    }
  }
});
