import { test, expect } from '@playwright/test';

test.describe('Visual Regression Testing - TutorialsNinja Login', () => {

  test.beforeEach(async ({ page }) => {
    // -------------------------------
    // Best Practices for Visual Tests
    // -------------------------------

    // Set consistent viewport
    await page.setViewportSize({ width: 1280, height: 800 });

    // Disable animations & transitions
    await page.addStyleTag({
      content: `
        * {
          animation: none !important;
          transition: none !important;
        }
      `
    });
  });

  test('Login flow visual regression test', async ({ page }) => {

    // -------------------------------
    // 2.1 Implement Visual Test
    // -------------------------------
    await page.goto(
      'https://tutorialsninja.com/demo/index.php?route=account/login'
    );

    // -------------------------------
    // 2.2 Create / Use Baseline Image
    // -------------------------------
    // First run:
    //   npx playwright test --update-snapshots
    // Creates baseline images
    // Next runs compare against baseline

    await expect(page).toHaveScreenshot('login-page.png', {

      // -------------------------------
      // 2.4 Handle Acceptable Differences
      // -------------------------------

      // Mask dynamic elements (if any)
      mask: [
        page.locator('#content') // Example: dynamic messages
      ],

      // Allow minor pixel differences
      maxDiffPixelRatio: 0.01, // 1% tolerance

      // Capture full page
      fullPage: true
    });

    // -------------------------------
    // Perform Login
    // -------------------------------
    await page.fill('#input-email', 'hk01@gmail.com');
    await page.fill('#input-password', '1432');
    await page.click('input[value="Login"]');

    // -------------------------------
    // 2.3 Run Test and Compare Results
    // -------------------------------
    await expect(page).toHaveScreenshot('my-account-page.png', {
      maxDiffPixelRatio: 0.01,
      fullPage: true
    });
  });

});
