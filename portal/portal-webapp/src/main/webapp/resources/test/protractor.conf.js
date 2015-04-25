/**
 * Created with IntelliJ IDEA.
 * User: damon
 * Date: 14-7-25
 * Time: 下午6:04
 * To change this template use File | Settings | File Templates.
 */
exports.config = {
    allScriptsTimeout: 11000,
    specs: [
        'e2e/*.js'
    ],

    capabilities: {
        'browserName': 'chrome'
    },

    baseUrl: 'http://localhost:9000/',

    framework: 'jasmine',

    jasmineNodeOpts: {
        defaultTimeoutInterval: 30000
    }
}