import * as THREE from 'three'

const scene = new THREE.Scene()
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000)

const renderer = new THREE.WebGLRenderer()
renderer.setSize(window.innerWidth, 400)
renderer.setClearColor(0xffffff)
document.body.appendChild(renderer.domElement)

const geometry = new THREE.BoxGeometry(1, 1, 1)

const material1 = new THREE.MeshBasicMaterial({color: 0x00ff00})
const material2 = new THREE.MeshBasicMaterial({color: 0xff0000})
const material3 = new THREE.MeshBasicMaterial({color: 0x0000ff})

const cube1 = new THREE.Mesh(geometry, material1)
const cube2 = new THREE.Mesh(geometry, material2)
const cube3 = new THREE.Mesh(geometry, material3)

scene.add(cube1)
scene.add(cube2)
scene.add(cube3)

cube1.position.set(-2, 0, 0)
cube2.position.set(0, 0, 0)
cube3.position.set(2, 0, 0)

camera.position.z = 5

function animate() {
  requestAnimationFrame(animate)
  cube1.rotation.x += 0.01
  cube1.rotation.y += 0.01

  cube2.rotation.x -= 0.01
  cube2.rotation.y -= 0.01

  cube3.rotation.x += 0.01
  cube3.rotation.y -= 0.01

  renderer.render(scene, camera)
}

animate()
