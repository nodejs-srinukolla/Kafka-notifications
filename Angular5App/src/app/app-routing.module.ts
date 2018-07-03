import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MessageComponent } from './message/message.component';
import { ArchitectureComponent } from './architecture/architecture.component';
import { AboutComponent } from './about/about.component';
import { DFDComponent } from './dfd/dfd.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'messages/:id', component: MessageComponent },
  { path: 'architecture', component: ArchitectureComponent },
  { path: 'about', component: AboutComponent },
  { path: 'dfd', component: DFDComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
