import { test } from '@playwright/test';
import { Eyes, ClassicRunner } from '@applitools/eyes-playwright';

test('TutorialsNinja Login - Visual Validation', async ({ page }) => {

  const runner = new ClassicRunner();
  const eyes = new Eyes(runner);

  try {
    // Open Applitools test
    await eyes.open(
      page,
      'TutorialsNinja App',          // Application name
      'Login Flow Visual Test'       // Test name
    );

    // Navigate to Login page
    await page.goto('https://tutorialsninja.com/demo/index.php?route=account/login');

    // Visual checkpoint: Login Page
    await eyes.checkWindow('Login Page');

    // Perform login
    await page.fill('#input-email', 'hk01@gmail.com');
    await page.fill('#input-password', '1432');
    await page.click('input[value="Login"]');

    // Visual checkpoint: My Account Page
    await eyes.checkWindow('My Account Page');

    // Close Eyes (ends the visual test)
    await eyes.close();

  } finally {
    // Get all test results
    const results = await runner.getAllTestResults();
    console.log(results);
  }

});
