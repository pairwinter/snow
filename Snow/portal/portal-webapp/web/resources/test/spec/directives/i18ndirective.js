'use strict';

describe('Directive: i18nDirective', function () {

  // load the directive's module
  beforeEach(module('snowApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<i18n-directive></i18n-directive>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the i18nDirective directive');
  }));
});
