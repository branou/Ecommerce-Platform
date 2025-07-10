import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import { inject } from '@angular/core';
import { AuthGuardData, createAuthGuard } from 'keycloak-angular';

const isAccessAllowed = async (
  route: ActivatedRouteSnapshot,
  _: RouterStateSnapshot,
  authData: AuthGuardData
): Promise<boolean | UrlTree> => {
  const { authenticated, grantedRoles, keycloak } = authData;

  const requiredRole = route.data['role'];
  if (!requiredRole) {
    return false;
  }

  const hasRequiredRole = (role: string): boolean =>
    Object.values(grantedRoles.resourceRoles).some((roles) => roles.includes(role));

  if (authenticated && hasRequiredRole(requiredRole)) {
    return true;
  }

  // 👇 This will show the Keycloak login page if not authenticated
  if (!authenticated) {
    await keycloak.login(); // ✅ Redirects to Keycloak login page
    return false; // You must return something (this is ignored because redirect happens)
  }

  // 👇 If authenticated but lacks role, redirect to /forbidden
  const router = inject(Router);
  return router.parseUrl('/forbidden');
};

// Export the custom guard
export const canActivateAuthRole = createAuthGuard<CanActivateFn>(isAccessAllowed);
