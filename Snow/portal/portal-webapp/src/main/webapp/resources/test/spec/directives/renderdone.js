'use strict';

describe('Directive: renderDone', function () {

  // load the directive's module
  beforeEach(module('snowApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<render-done></render-done>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the renderDone directive');
  }));
});
