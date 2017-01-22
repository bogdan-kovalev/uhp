import Ember from "ember";

export default Ember.Controller.extend({
  navigator: Ember.inject.service(),
  session: Ember.inject.service(),
  usersService: Ember.inject.service(),

  leftSideBarOpen: false,

  actions: {
    openProfile () {
      this.transitionToRoute('profile');
    },
    invalidateSession() {
      this.get('session').invalidate();
    }
  }
});
